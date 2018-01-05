# Architecture Guidelines

* Use Dagger2 for Dependencies Injection								
* Use Gson to Parser JSON 
* Use Retrofit2 as HttpClient [see more](http://square.github.io/retrofit/)
* Use RxJava/RxAndroid for better performance
* Call REST API by Using RxJava-Retrofit2
* Database must use Realm [see more](https://realm.io/docs/java/latest/)
* Using Glide to load Image for better performance [see more](https://github.com/bumptech/glide)
* Use ButterKnife to avoid findViewById for clean code [see more](https://github.com/JakeWharton/butterknife)
* Use EventBus to simplifies the communication between components [see more](https://github.com/greenrobot/EventBus)
* Use Material Dialogs with material style [see more](https://github.com/afollestad/material-dialogs)

The architecture of our Android apps is based on the [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)(https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) (Model View Presenter) pattern.
 
[see more]: (https://codelabs.developers.google.com/codelabs/android-testing/index.html)

* __View (UI layer)__: 
    * Define an interface that your View (Activity, Fragment, View) is going to implement. The interface will exposes the method to Presenter to interact with.
    * Your View implement the interface
    * Inject presenter to View
    * Attach View to presenter
    * View only know __HOW__ to display
* __Presenter__: 
    * Implement methods that the View requires to perform the nessary actions.
    * One finish using interface to interact with View
    * Presenter know __WHEN__ display
* __Model (Data Layer)__: 
    * Dispatch request using DataManager
    * Return Observables for Presenter
    * Model know __WHAT__ display

