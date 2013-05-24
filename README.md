15boilerplate
=============

A bit of boilerplate for building web applications using Servlets. This structure allows for rapid development of static assets without restarting the Jetty server (changes to Java source do require a restart though).

Getting Started
---------------

1. Download the required Java libraries and add them to the `libs/` folder.
2. Make your own package structure in the `app/main` folder.
3. Map any controllers to their contexts in the `conf/urls.json` file.
4. Run `ant`.
5. Open <http://localhost:8800>.
6. You can edit static assets (HTML, CSS, JS, views) while the server is running.

Deployment Descriptor
---------------------

The `conf` (configuration) folder contains a Python script to generate a **very simple** `web.xml` file from a JSON file that maps fully-qualified Java Servlets to their desired contexts. More complex `web.xml` files should be placed in the configuration folder if needed and copied to the WAR directory in the Ant run task (replacing the Python script).

For example:

    {
        "com.whymarrh.boilerplate.ExampleController": "/example"
    }

This sends requests for <http://localhost:8800/example> to the `ExampleController`.

Controllers
-----------

Controllers extend `javax.servlet.http.HttpServlet`.

Folder Structure
----------------

    .
    ├── README.md
    ├── app
    │   └── main
    │       └── java
    │           └── com
    │               |── whymarrh
    │               |   └── boilerplate (various utility classes)
    │               |       └── controller (example controllers)
    │               └── another (you generate this folder and its contents)
    │                   └── folder
    |                       |── (anything else)
    │                       └── controller (controllers can go in here)
    ├── build.xml
    ├── conf (urls.json file, if used, otherwise web.xml)
    ├── libs (JAR files)
    │   └── README.md
    ├── public (HTML, JS, CSS, etc.)
    │   |── js
    │   |── imgs
    │   └── css
    └── views (all the views as *.mustache.html files)
        └── partials (partial views to be used in other views)

View Rendering
--------------

Use the [jmustache library by Sam Skivert][1] to render views dynamically.

  [1]:https://github.com/samskivert/jmustache
