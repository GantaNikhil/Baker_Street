const express=require('express');
const {isLoggedin}=require('../middleware/isAuthenticated')
const courseRouter=express.Router();
const {wrapAsync}=require('../utils/asyncErrorHandler')
const {createCourse,getCourses,deleteCourse}=require('../controllers/courses')



courseRouter.get('/',isLoggedin,wrapAsync(getCourses));
courseRouter.post('/',isLoggedin,wrapAsync(createCourse));
courseRouter.delete('/',isLoggedin,wrapAsync(deleteCourse));


module.exports={courseRouter}
