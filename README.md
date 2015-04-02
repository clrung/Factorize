# Factorize
I wrote this Android project during the summer of 2011 to learn how to run a process in the background (using a [Thread](http://developer.android.com/reference/java/lang/Thread.html)) and display its progress using a [ProgressBar](http://developer.android.com/reference/android/widget/ProgressBar.html).

I chose to write an application that finds all of the factors of a given number because factorization can be a time intensive task.  [Factorization](http://en.wikipedia.org/wiki/Factorization), or factoring, is the “decomposition of an object (for example, a number, a polynomial, or a matrix) into a product of other objects, or factors, which when multiplied together give the original” (Wikipedia).  For example, the factorization of 24 is 2 * 2 * 2 * 3.

## Usage
The project was built to run on Android 2.3.3 (API 10), but it should run without issues on any Android OS, from API 1 through current, since I only used basic Android UI elements.  Import this project into Eclipse and run `FactorizeActivity` to launch the application.

## Screenshots
![start](https://raw.githubusercontent.com/clrung/Factorize/master/screenshots/start.png)
![progress](https://raw.githubusercontent.com/clrung/Factorize/master/screenshots/progress.png)
![finish](https://raw.githubusercontent.com/clrung/Factorize/master/screenshots/finish.png)