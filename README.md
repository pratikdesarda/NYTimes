# NYTimes

This is a demo app using NYTimes API's. The app is having two bottom navigation tabs as "Home" and "Books" and on the home screen, it is also having search bar with the filter. Some of the features are as follows,
1. Top stories on app launch/home screen.
2. Search for the articles and render the articles having the searched keyword onto the home screen. We are having two types of search scenarios.
i.  Search using only keywords - User can search using keywords only.
ii. Search using keywords along with specified categories - User can select any category and search using keyword into those specific categories.
3. On the click of "Books" tab, the user will navigate to best sellers list. If the user clicks on any of the best sellers, the user will navigate to an overview of top 5 ranked books. There is a history icon on the overview page. If the user clicks on the history icon, the user will navigate to history screen for that particular book.

Libraries used,

1. Retrofit Dependencies.
com.squareup.retrofit2:retrofit:2.3.0
com.squareup.retrofit2:converter-jackson:2.3.0
com.squareup.okhttp3:okhttp:3.7.0

2. Cardview Dependency
com.android.support:cardview-v7:25.3.1

3. Picasso Dependency
com.squareup.picasso:picasso:2.4.0

NYTimes API Link: https://developer.nytimes.com/ 
