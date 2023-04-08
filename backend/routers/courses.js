const express=require('express');
const {isLoggedin}=require('../middleware/isAuthenticated')
const {isProf}=require('../middleware/isProfessor')
const courseRouter=express.Router();
const {wrapAsync}=require('../utils/asyncErrorHandler')
const {createCourse,getCourses,deleteCourse,uploadMaterials,uploadAssignments,submitAssignments,gradeAssignment}=require('../controllers/courses')
const {multerAttachmentFileUploader}=require('../utils/multerUploader');
const { isStudent } = require('../middleware/isStudent');


courseRouter.get('/',isLoggedin,isProf,wrapAsync(getCourses));
courseRouter.post('/',isLoggedin,isProf,wrapAsync(createCourse));
courseRouter.delete('/',isLoggedin,isProf,wrapAsync(deleteCourse));
courseRouter.post('/materials',isLoggedin,isProf,multerAttachmentFileUploader,wrapAsync(uploadMaterials))
courseRouter.post('/assignments',isLoggedin,isProf,multerAttachmentFileUploader,wrapAsync(uploadAssignments))
courseRouter.post('/assignments/submissions',isLoggedin,isStudent,multerAttachmentFileUploader,wrapAsync(submitAssignments))
courseRouter.post('/assignments/submissions/grade',isLoggedin,isProf,wrapAsync(gradeAssignment))

module.exports={courseRouter}
