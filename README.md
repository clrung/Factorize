# Factorize
I wrote this Android project during the summer of 2011 to learn how to run a process in the background (using a [Thread](http://developer.android.com/reference/java/lang/Thread.html)) and display its progress using a [ProgressBar](http://developer.android.com/reference/android/widget/ProgressBar.html).

I chose to write an application that finds all of the factors of a given number because factorization can be a time intensive task.  [Factorization](http://en.wikipedia.org/wiki/Factorization), or factoring, is the “decomposition of an object (for example, a number, a polynomial, or a matrix) into a product of other objects, or factors, which when multiplied together give the original” (Wikipedia).  For example, the factorization of 24 is 2 * 2 * 2 * 3.

## Demo
<p align="center">
  <img src="https://raw.githubusercontent.com/clrung/Factorize/master/README%20Assets/FactorizeDemo.gif" alt="Demo"/>
</p>
This demonstrates the application finding all of the factors of the number 5478576.  Notice how it shows the progress of computing the factorization–it shows 114137 before computing that there are two factors of 114137, 311 and 367.

## Usage
The project was built to run on Android 2.3.3 (API 10), but it should run without issues on any Android OS, from API 1 through current, since I only used basic Android UI elements.  Import this project into Eclipse and run `FactorizeActivity` to launch the application.