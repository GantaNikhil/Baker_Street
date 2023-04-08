const uuid=require('uuid').v4
const {getClient}=require('../db')
const client=getClient();
const {ObjectId}=require('mongodb')
const _db=client.db(process.env.DBNAME);
const {mybucket}=require('../utils/gcp')
const {ExpressError}=require('../utils/customErrorHandler')
const getCourses= async (req,res)=>{
    const profid=req.firebaseuserid

    const courses=await _db.collection('courses').aggregate([
        {
            $match:{profid:profid}
        },
        {
            $project: {
              profid:0
            }
        }
    ]).toArray()
    res.status(200).json({"courses":courses})
    
}

const createCourse = async (req,res)=>{
    //course code, name
    const {code,name}=req.body
    // const courseid=uuid()
    const profid=req.firebaseuserid
    // create course
    const course={
        code,
        name,
        materials:[],
        profid
    }
    //need to write transaction
    const insertedDoc=await _db.collection('courses').insertOne(course);
    const course_id=insertedDoc.insertedId
    await _db.collection('users').updateOne({userid:profid},
        {$push:{courses: course_id}}
    )
    res.status(200).json({"message":"Course Created"})
}

const deleteCourse= async (req,res)=>{
    let id=new ObjectId(req.body._id)
    const profid=req.firebaseuserid
    await _db.collection('courses').deleteOne({_id:id})
    await _db.collection('professors').updateOne({userid:profid},
        {$pull :{courses: id}}
    )
    //we need to delete the registrations of students for this course
    //from the registrations collection
    res.status(200).json({"message":"course deleted"})

}

const uploadMaterials = async (req,res)=>{
    const courseid=new ObjectId(req.body.courseid)
    if(!req.file){
        throw new ExpressError("Missing material to attach to the course",501);
    }
    const options={
        destination:req.file.filename, //name of the file with which we want our uploaded file to store with- basically the name of the file in bucket
        preconditionOpts:{ifGenerationMatch:0}
    }
    const output=await mybucket.upload(req.file.path,options);
    const publicURL=`https://storage.googleapis.com/${output[0].metadata.bucket}/${req.file.filename}`
    const newElement={
        url:publicURL,
        createdAt:new Date()
    }
    await _db.collection('courses').updateOne({_id:courseid},{
        $push:{ materials: newElement}
    })
    return res.status(200).json({message:"File attatched"})
}

const uploadAssignments = async (req,res)=>{
    const courseid=new ObjectId(req.body.courseid)
    const text=req.body.description
    if(!req.file){
        throw new ExpressError("Missing assigment to attach to course",501);
    }
    const options={
        destination:req.file.filename, //name of the file with which we want our uploaded file to store with- basically the name of the file in bucket
        preconditionOpts:{ifGenerationMatch:0}
    }
    const output=await mybucket.upload(req.file.path,options);
    const publicURL=`https://storage.googleapis.com/${output[0].metadata.bucket}/${req.file.filename}`
    const newElement={
        url:publicURL,
        text,
        courseid,
        createdAt:new Date()
    }
    await _db.collection('assignments').insertOne(newElement);
    return res.status(200).json({message:"Assignment uploaded"})
}

const submitAssignments= async (req,res)=>{
    const courseid=new ObjectId(req.body.courseid)
    const assigmentid=new ObjectId(req.body.assigmentid)
    if(!req.file){
        throw new ExpressError("Missing file to submit",501);
    }
    const options={
        destination:req.file.filename, //name of the file with which we want our uploaded file to store with- basically the name of the file in bucket
        preconditionOpts:{ifGenerationMatch:0}
    }
    const output=await mybucket.upload(req.file.path,options);
    const publicURL=`https://storage.googleapis.com/${output[0].metadata.bucket}/${req.file.filename}`
    const newElement={
        url:publicURL,
        assigmentid,
        courseid,
        createdAt:new Date()
    }
    await _db.collection('submissions').insertOne(newElement);
    return res.status(200).json({message:"Solution Submitted, file uploaded"})


}


const gradeAssignment= async (req,res)=>{
    const submissionid=new ObjectId(req.body.submissionid)
    const grade=req.body.grade
    await _db.collection('submissions').updateOne({_id:submissionid},
    {
        $set:{grade:grade}
    })
    res.status(200).json({"message":"Assignment graded"})
}
module.exports={createCourse,getCourses,deleteCourse,uploadMaterials,uploadAssignments,submitAssignments,gradeAssignment}