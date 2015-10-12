# FrequencyAnalysisCrypto
Java application that attempts to decrypt a document that has been encrypted using a substitution cipher encryption algorithm. Then the program will estimate the accuracy of this approach using a technique called cross-validation. Focus is not on encryption or decryption algorithms. Instead, focus is on writing good quality, reusable and easily maintainable code.


Application was developed in a modular fashion and was designed for extensibility. Overall, I tried to keep the data layer (reading the file), presentation layer (writing data) and the business logic layer (building the frequency model) independent from each other.

Divided the overall project into following packages:

* Crypto (package edu.upenn.cis573.hwk1.crypto)​: Responsible for the encryption/decryption functionality
* IO (p​ackage edu.upenn.cis573.hwk1.io)​: Responsible for reading the data and outputting the results
* Crypto:​
  * Interface I​Crypto​defining the encryption/ decryption functionality. This lets the
functionality be abstract letting the user implement any kind of encryption / decryption algorithm (like a simple substitution cipher or even a complex symmetric cryptography) under the hood.
  * CharacterFrequencyAnalysis​class which builds the frequency model. Methods have been declared in a way such that it can be used to build the frequency model from both the training data and the test data. The member variables are the HashMap data structure to store the character frequencies and the string on which frequency analysis needs to be done. This class also contains methods to build the frequency counter and to sort the HashMap by value (character frequency) and return the final frequency model.
  * SubstitutionCipherCrypto ​implementing the ICrypto interface which uses Substitution cipher for encryption and frequency analysis for decryption. It stores the training data, test data and the frequency model built from the training data as member variables. It also contains a method for cross validation which compares the cipher text and plain text and computes the accuracy.
* IO:
  * Interface I​ReadData​abstracting the functionality of reading the data from any source (database, web service, files) and any format of data (xml, json, txt) and IPresentation​which abstracts the presentation of the cross­validation results.
The data that the methods in every class work on and encapsulates were used as the fields. Also, care has been taken to ensure the visibility of the methods and variables. For instance, the CrossValidationInfo has members which are visible at package level (edu.upenn.cis573.hwk1.crypto) and have public getter methods so that it can be used in the PresentationConsoleOutput​class in a different package. This ensures that these members are only modified by the Crypto package and not elsewhere.

Some distinct ways in which the design is "good":

* Abstract functionality through Interfaces : ​Interfaces provide a very easy way​to define APIs, so that all concrete implementations of the interfaces provide the expected methods in each class.
* ICrypto a​bstracts the encryption and decryption functionality allowing the user to use any kind of complex encryption and decryption algorithms.
* IReadData r​epresenting the data layer which abstracts the reading of the input from any source. (database, web­service etc). Assumption is that everything is finally standardized in the form of files and file descriptors to these files are returned from the abstract function ​readToDirectory. S​o, if the input is to be read from a web service, the user can implement this functioanlality by overriding this abstract method. This interface also provides another method which abstracts reading different formats of data (XML, JSON). Assumption is here that everything is finally standardized in the form of text files. So, if the input is in XML format, the user can implement this interface overriding the r​eadToTextFile f​unction which could possible convert XML to txt.
* IPresentation r​epresenting the Presentation layer which presents the accuracy of the decryption algorithm used to any kind of standard output. Using the C​rossValidationInfo o​bject, we can output the results either on the console or any kind of graphical interface.
* Extensibility:I​nterfacesforencryption,readingdata,writingdataareprovided.The major advantage that comes with interfaces is that any developer who uses these interfaces and implements them, can easily add more functionality in addition to the existing functionality that he needs to conform to. For instance, he can create a new class Symmetric Crypto ​implementing ICrypto to add functionality to createandshare private­public key pairs in addition to the encryption and decryption functionality.
* Modular class design : Classes have been designed in a modular fashion such that new members and methods can be added easily to add more functionality. For instance , the crossvalidation results are storedin CrossValidation Info class. Additional information such as time taken to cross validate a document can be easily added independently without disturbing other parts of the code.
* Not easy to misuse : Classes don’t expose a series of public properties. This is a very bad practice. Consider the instance where a set of public properties are present in the class and which are populated from the constructor. If these are misused and modified, then there could be unexpected things could happen. Only the necessary members are made public, the rest are made


