# autoFillRegressionPlanInTestRail

In the Regression.java file, in line 67 (if nothing changed from your part)
in de listener for updateButton, the action performed is what needs to be
changed, in line 71, de function that the controller uses can be
updateFromTestRail (if you take the run results from testRail)
updateFromJenkins (if you take the run results from Jenkins).

To make it work, run the Main class, a window should appear
copy the related links, for test run to the left and test plan to 
the right, click update, be carefull because you still need to signIn
in Jenkins and in TestRail with your user and password