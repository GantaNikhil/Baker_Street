const express=require('express');
const {isLoggedin}=require('../middleware/isAuthenticated')
const {isProf}=require('../middleware/isProfessor')
const courseRouter=express.Router();
const {wrapAsync}=require('../utils/asyncErrorHandler')
const {createCourse,getCourses,deleteCourse,uploadMaterials,uploadAssignments,submitAssignments,gradeAssignment, Comments, getAllMaterials, getAllSubmissions, DeleteComments, UpdateComments, UploadAnnouncement, DeleteAnnouncement}=require('../controllers/courses')
const {multerAttachmentFileUploader}=require('../utils/multerUploader');
const { isStudent } = require('../middleware/isStudent');

// create,delete,get all courses 
courseRouter.get('/',isLoggedin,isProf,wrapAsync(getCourses));
courseRouter.post('/',isLoggedin,isProf,wrapAsync(createCourse));
courseRouter.delete('/',isLoggedin,isProf,wrapAsync(deleteCourse));

// get all assignments and upload Assignments for professor
courseRouter.get('/assignments',isLoggedin,wrapAsync(getAllMaterials));
courseRouter.post('/assignments',isLoggedin,isProf,multerAttachmentFileUploader,wrapAsync(uploadAssignments))

// submissions by students are handled and retreived if professor needs
courseRouter.get('/assignments/submissions',isLoggedin,isProf,wrapAsync(getAllSubmissions));
courseRouter.post('/assignments/submissions',isLoggedin,isStudent,multerAttachmentFileUploader,wrapAsync(submitAssignments))

// professor gives grade and a feeback statement
courseRouter.post('/assignments/submissions/grade',isLoggedin,isProf,wrapAsync(gradeAssignment))

// materials posted by professor are handled
courseRouter.get('/materials',isLoggedin,wrapAsync(getAllMaterials));
courseRouter.post('/materials',isLoggedin,isProf,multerAttachmentFileUploader,wrapAsync(uploadMaterials))

// announcements by professor are handled
courseRouter.post('/announcements',isLoggedin,isProf,wrapAsync(UploadAnnouncement));
courseRouter.delete('/announcements',isLoggedin,isProf,wrapAsync(DeleteAnnouncement));

// comments by both professor and students are handled
courseRouter.post('/comments',isLoggedin,wrapAsync(Comments));
courseRouter.put('/comments',isLoggedin,wrapAsync(UpdateComments));
courseRouter.delete('/comments',isLoggedin,wrapAsync(DeleteComments));

module.exports={courseRouter}
