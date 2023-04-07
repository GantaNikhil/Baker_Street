const uuid=require('uuid').v4
const {getClient}=require('../db')
const client=getClient();
const {ObjectId}=require('mongodb')
const _db=client.db(process.env.DBNAME);

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
    const courseid=uuid()
    const profid=req.firebaseuserid
    // create course
    const course={
        code,
        name,
        courseid,
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
    await _db.collection('users').updateOne({userid:profid},
        {$pull :{courses: id}}
    )
    //we need to delete the registrations of students for this course
    //from the registrations collection
    res.status(200).json({"message":"course deleted"})

}
module.exports={createCourse,getCourses,deleteCourse}