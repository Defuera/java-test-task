# java-test-task
Trivial android test task written in Java

Task description:
 
With given API fetch list of data, containing users by departments and display them in a scrollable list.
The application should consist of two views:
o Main view:
▪ The main view should contain a list with employees categorized per department;
▪ Each item in the list should display full name and thumbnail of the photo (if present);
▪ Clicking an item will cause the application to open the detail view.
 
o Detail view:
▪ The detail view should display the following information of the selected employee:
● Full name;
● Thumbnail of the photo;
● Department;
● Role. ▪ A button should be available causing the default email client to be opened with the "To" address filled in with the email address of the employee;
▪ Clicking the photo should open an image viewer allowing the user to see a bigger version of the photo.
 
 
Notes:
 
Application is implemented with MVP pattern and clean architecture in mind. Package structure is feature based, so you can see two main packages: index and detailed.
 
For testability and IoC Dagger2 is applied. For background tasks RxJava. For networking OkHttp + Retrofit.
 
Couple of my local libraries used to speed up the process and provide cleaner code. LilWidgets provides loader widget with convenient showLoader/showError interface. ElAdapter as a quickest possible way to implement RecyclerAdapter and handle items clicks.
 
Additional features - in memory cache implemented via MemoryCacheMembersDataSource class and tested with unit test.
 
Application supports rotation but do not support tablet screen.
 
possible improvements
- Add Subcomponent for list feature with it's own Module
- Reduce the number of containers for detailed view
 
Please note: in debug note delay added to load data from network to show loading state. FetchMembersResponseDeserializer.class is added to abstract backend api, since I wasn't satisfied with data structure provided.
 
