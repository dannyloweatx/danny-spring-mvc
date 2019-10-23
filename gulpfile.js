var gulp = require('gulp');
var argv = require('yargs').argv;
var defaultTargetWarDirectory = "target/danny-spring-mvc";

gulp.task('copy-js-lib', function() {
    var npmStream = gulp.src([
            'node_modules/**/*.js',
            'node_modules/**/*.css'
        ], {base: 'node_modules/'});
   
   var libtarget = defaultTargetWarDirectory + '/web-libs';
    return npmStream.pipe(gulp.dest(libtarget));
});

gulp.task('default', ['build']);