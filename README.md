# FlickrProject
I have followed MVP architecture in this implementation. I would have implemented Dagger for the dependency injection in live project.
In addendum, I didn't write code to check for the internet connection for the brevity of code.
Addition of Progress Bar and Animation can add more user interaction. 


Write a mobile app that uses the Flickr image search API and shows the results in a 3-column scrollable view.

Requirements and notes:
•	The app should display the first page of results returned by the API. However, see the bonus task section for additional optional requirements.
•	The app should correctly handle orientation changes (without requiring android:configChanges in the manifest file) i.e. image search term and results should remain after screen rotation.
•	Don’t worry about supporting old versions of Android, a minSdkVersion of 21 or later is fine. There are bonus points for the inclusion of Material Design elements.
•	Feel free to use the technologies you are most comfortable with. This includes any open-source third-party libraries such as Butterknife, Gson, Retrofit, OkHttp, Picasso, RxJava, etc.

Bonus tasks:
•	Support pagination – Extend your app to support endless scrolling i.e. automatically requesting and displaying more images when the user scrolls to the bottom of the list.
•	Add one or two unit tests to demonstrate your knowledge of automated testing.

Your priorities should be:
1.	A working app. Shortcuts are fine given the time constraints, but please justify them and explain better solutions you would have implemented with more time in a README file.
2.	Clean code and architecture. We would like you to write “production ready” code that you would be proud to submit as an open source project. We’d prefer you to write clean code and not meet all the requirements, rather than try to meet all the requirements and write code you’re not proud of.

We expect this to take a maximum of 2-3 hours so there’s no need to implement features that would require more time than that. A concise and readable codebase that accomplishes all of the above requirements is the goal, so don’t try to do any more than is required to solve the problem cleanly.
Include both a pre-built APK and complete source code in your submission. Please exclude all auto-generated files and build output from the source archive.
Good luck!
Flickr API
API Key: 96358825614a5d3b1a1c3fd87fca2b47
You can make a call to the Flickr API to return a JSON object with a list of photos.
https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=96358825614a5d3b1a1c3fd87fca2b47&text=kittens&format=json&nojsoncallback=1 
The text parameter should be replaced with the query that the user enters into the app.
The JSON response you'll receive will have items described like this example.
{
	"id": "39593986652",
	"owner": "36739135@N04",
	"secret": "0ec416669f",
	"server": "4740",
	"farm": 5,
	"title": "IMG_5508",
	"ispublic": 1,
	"isfriend": 0,
	"isfamily": 0
},
You can use these parameters to get the full URL of the photo:
http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
So, using our example from before, the URL would be
http://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg
If interested, more documentation about the search endpoint can be found at https://www.flickr.com/services/api/explore/flickr.photos.search. If you have any problems with the specified API key, then you can generate your own at https://www.flickr.com/services/api/misc.api_keys.html.


