small-batch-maker
=================

Simple application that gets a directory and makes batches of files containing a maximum number of files AND maximum size in bytes.

Why I built it:

I 30k files that sum up to 2.5GB of data that I have to upload through a very unstable channel, using an application that cannot be trusted. So, I need to break the whole operation down to small batches of files within the same directory, restrained by size and number of files.

Those batches can then be paralellized and executed my multiple developers at the same time.

What is the output:

The output is a string containing the names of the files to be uploaded. Some metadata can be available to inform the uploader of how long it should take.

This application is still under initial development.


