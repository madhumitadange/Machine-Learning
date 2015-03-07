/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ml_project;

import java.io.File;

/**
 *
 * @author madhumita
 */
public class test {
     public static void main (String[] args)  throws Exception {
                ML_Project c = new ML_Project();
                
                String models[]={"F:\\KNN3.model","F:\\J48.model","F:\\\\naiveb.model","F:\\\\smo3.model","F:\\adaDS.model","F:\\knn1.model","F:\\knn3.model"};
              for(int i=0;i<models.length;i++){
                String SMOmodel =models[i];
                
                int globalcnt=0;
                int totalFiles=0;
                String testCoursepath= "F:\\test_uni\\course\\";
                
                File testCoursefolder = new File(testCoursepath);

                File[] listOfTestCourseFiles = testCoursefolder.listFiles();
                int cnt=0;
                int noCourseFiles= listOfTestCourseFiles.length;
                totalFiles= totalFiles+noCourseFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestCourseFiles) {
                    if (file.isFile()) {
                    if( c.classify(testCoursepath+file.getName(),SMOmodel).equalsIgnoreCase("Course")){
                    cnt++;
                    globalcnt++;
                       // System.out.println("cnt="+cnt);
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for course test"+acc);
     
     String testDeptpath= "F:\\test_uni\\department\\";
                
                File testDeptfolder = new File(testDeptpath);

                File[] listOfTestDeptFiles = testDeptfolder.listFiles();
                cnt=0;
                int noDeptFiles= listOfTestDeptFiles.length;
                totalFiles= totalFiles+noDeptFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestDeptFiles) {
                    if (file.isFile()) {
                         // System.out.println(c.classify(testDeptpath+file.getName(),SMOmodel));
                    if( c.classify(testDeptpath+file.getName(),SMOmodel).equalsIgnoreCase("Department")){
                    cnt++;
                    globalcnt++;
                        //System.out.println(c.classify(testDeptpath+file.getName(),SMOmodel));
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                //double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for dept test "+100*(double)cnt/noDeptFiles);
                
                 String testFacultypath= "F:\\test_uni\\faculty\\";
                
                File testFacultyfolder = new File(testFacultypath);

                File[] listOfTestFacultyFiles = testFacultyfolder.listFiles();
                cnt=0;
                int noFacultyFiles= listOfTestFacultyFiles.length;
                totalFiles= totalFiles+noFacultyFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestFacultyFiles) {
                    if (file.isFile()) {
                         // System.out.println(c.classify(testFacultypath+file.getName(),SMOmodel));
                    if( c.classify(testFacultypath+file.getName(),SMOmodel).equalsIgnoreCase("Faculty")){
                    cnt++;
                    globalcnt++;
                        //System.out.println(c.classify(testFacultypath+file.getName(),SMOmodel));
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                //double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for Faculty test "+100*(double)cnt/noFacultyFiles);
                
                 String testProjectpath= "F:\\test_uni\\project\\";
                
                File testProjectfolder = new File(testProjectpath);

                File[] listOfTestProjectFiles = testProjectfolder.listFiles();
                cnt=0;
                int noProjectFiles= listOfTestProjectFiles.length;
                totalFiles= totalFiles+noProjectFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestProjectFiles) {
                    if (file.isFile()) {
                         // System.out.println(c.classify(testProjectpath+file.getName(),SMOmodel));
                    if( c.classify(testProjectpath+file.getName(),SMOmodel).equalsIgnoreCase("Project")){
                    cnt++;
                    globalcnt++;
                        //System.out.println(c.classify(testProjectpath+file.getName(),SMOmodel));
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                //double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for Project test "+100*(double)cnt/noProjectFiles);
                
                 String testStaffpath= "F:\\test_uni\\staff\\";
                
                File testStafffolder = new File(testStaffpath);

                File[] listOfTestStaffFiles = testStafffolder.listFiles();
                cnt=0;
                int noStaffFiles= listOfTestStaffFiles.length;
                totalFiles= totalFiles+noStaffFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestStaffFiles) {
                    if (file.isFile()) {
                         // System.out.println(c.classify(testStaffpath+file.getName(),SMOmodel));
                    if( c.classify(testStaffpath+file.getName(),SMOmodel).equalsIgnoreCase("Staff")){
                    cnt++;
                    globalcnt++;
                        //System.out.println(c.classify(testStaffpath+file.getName(),SMOmodel));
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                //double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for Staff test "+100*(double)cnt/noStaffFiles);
                
                 String testStudentpath= "F:\\test_uni\\student\\";
                
                File testStudentfolder = new File(testStudentpath);

                File[] listOfTestStudentFiles = testStudentfolder.listFiles();
                cnt=0;
                int noStudentFiles= listOfTestStudentFiles.length;
                totalFiles= totalFiles+noStudentFiles;
               // System.out.println("nofiles"+noCourseFiles);
                for (File file : listOfTestStudentFiles) {
                    if (file.isFile()) {
                         // System.out.println(c.classify(testStudentpath+file.getName(),SMOmodel));
                    if( c.classify(testStudentpath+file.getName(),SMOmodel).equalsIgnoreCase("Student")){
                    cnt++;
                    globalcnt++;
                        //System.out.println(c.classify(testStudentpath+file.getName(),SMOmodel));
                    }
                      //  System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				//System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
                    }
                }
                //double acc =100*(double)cnt/noCourseFiles;
                System.out.println("Acuurcy for Student test "+100*(double)cnt/noStudentFiles);
                System.out.println("Acuurcy for  test Set:  "+100*(double)globalcnt/totalFiles + " For "+SMOmodel);
     }
     }
  
}
    

