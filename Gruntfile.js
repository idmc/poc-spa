'use strict';

module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        banner: '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - ' +
        '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
        '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
        '* Copyright (c) <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>;' +
        ' Licensed <%= _.pluck(pkg.licenses, "type").join(", ") %> */\n',
        watch: {
            js: {
                files: '<%= jshint.all %>',
                tasks: ['concat', 'rsync:devjs']
            },
            less: {
                files: ['src/main/web/less/*.less'],
                tasks: ['less', 'rsync:devless']
            }
        },
        concat: {
            options: {
                banner: '<%= banner %>',
                stripBanners: true
            },
            dist: {
                src: [
                    'bower_components/angular/angular.js',
                    'bower_components/angular-route/angular-route.js',
                    'bower_components/angular-resource/angular-resource.js',
                    'bower_components/ui-bootstrap/dist/ui-bootstrap-tpls-0.12.1.js',
                    'bower_components/leaflet/dist/leaflet.js',
                    'bower_components/angular-leaflet-directive/dist/angular-leaflet-directive.js',
                    'src/main/web/js/app.js',
                    'src/main/web/js/navbardirective.js',
                    'src/main/web/js/*'
                ],
                dest: 'src/main/resources/static/js/<%= pkg.name %>.js'
            }
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc',
                "force": true
            },
            all: [
                'Gruntfile.js',
                'src/main/web/js/controllers/*',
                'src/main/web/js/services/*',
                'src/main/web/js/*.js'
            ]
        },
        uglify: {
            options: {
                banner: '<%= banner %>',
                sourceMap: 'src/main/resources/static/js/<%= pkg.name %>.js.map',
                sourceMappingURL: 'src/main/resources/static/js/<%= pkg.name %>.js.map',
                sourceMapPrefix: 2
            },
            dist: {
                src: '<%= concat.dist.dest %>',
                dest: 'src/main/resources/static/js/<%= pkg.name %>.min.js'
            }
        },
        less: {
            development: {
                options: {
                    paths: ["assets/css"]
                },
                files: {
                    "src/main/resources/static/style/app.min.css": "src/main/web/less/main.less"
                }
            },
            production: {
                options: {
                    paths: ["assets/css"],
                    plugins: [
                        //new (require('less-plugin-autoprefix'))({browsers: ["last 2 versions"]}),
                        //new (require('less-plugin-clean-css'))(cleanCssOptions)
                    ],
                    modifyVars: {
                        imgPath: '"http://mycdn.com/path/to/images"',
                        bgColor: 'red'
                    }
                },
                files: {
                    "src/main/resources/static/style/app.min.css": "src/main/web/less/main.less"
                }
            }
        },
        karma: {
            unit: {
                configFile: 'karma.conf.js',
                background: true,
                singleRun: false
            }
        },
        rsync: {
            options: {
                args: ["--verbose"],
                recursive: true,
                syncDestIgnoreExcl: false
            },
            devjs: {
                options: {
                    src: "src/main/resources/static/js/",
                    dest: "target/classes/static/js/"
                }
            },
            devless: {
                options: {
                    src: "src/main/resources/static/style/",
                    dest: "target/classes/static/style"
                }
            }
        }

    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-rsync');
    grunt.loadNpmTasks('grunt-karma');

    grunt.registerTask('combine', ['concat:dist', 'uglify:dist', 'less']);

};