# VmDirectoryApp

Project is developed on the following lines


1) For code to be testable we need to use dependency injection architecture pattern, instead of using any library i have implemented with
pure dependency injection where we separate construction  and functional logic in to separate set of classes keeping both sets disjoint.
 a) AppCompositionRoot class is used for app level scope or singleton objects
 b) ScreenCompositionRoot class is used for activity level objects

2) We also need to keep Activities and fragments as leaner as possible and extracting any buisness logic into standalone classes 
for writing simpe unit tests as we cant construct these framework classes.

3) I have packaged the project using package by feature which is self explanatory to the new developers what actually is the project of.

4) I dont find any reason for this simple project to modularize for the present usecase, later we can do it as we identify them. We dont
need to modularize for the sake of it, which might make the development complex or uneasy.

5) Used usecases which provide better abstraction than repoistory pattern.

6) Developed the app keeping in mind for the accessibility, we can use the talkback to test for it.

7) I have used the simple UI, please pardon me on that. Used only viewBinding ignored dataBinding for now.

8) Added toolbar color based on favourite color of the employee in details screen.

9) We can use the onSaveInstanceState for orientation data savings instead of using viewmodel just for orientation benifits.

10) Added separate buildtypes for QA and prod. 
