SDG2
A Syntax Diagram Generator 2 (for ANTLR)

SDG2 allows to visualize textual syntax diagrams (ANTLR) 
as "railroad" diagrams.

Mark Malakanov
Max Andersen (adjusted this readme.txt to reflect reality and packaged it as a jar)

Usage: java net.mark_malakanov.sdg2.Main [-?|-help] [-width 600] [-blind] \
[-save image.ext] [-savehtml file.html [-savehtmldir dir] \
[-savehtmltitle "html Title"] [-savehtmlheader "html Header"] \
[-saveimgborder 0] [-saveimgtype ext] [-i] [input.g]


-blind		Runs SDG2 without showing GUI. Exits after one run.

-i		Reads grammar from standard input.

-?
-help
--help		Prints usage information

-save		Saves whole diagram into image file. 
		Format must be supported. PNG and JPG are.

-savehtml	Name of HTML document that unites images of rules saved into 
		separate files. 

-savehtmldir	A directory where to store images of rules. 
		It will be created if does not exist.

-savehtmlheader	A header of HTML document. (<H1> tag)

-savehtmltitle  Title of HTML document. (<TITLE> tag)

-saveimgborder	Width of a border around each image in HTML document.

-saveimgtype	Type of raster images when -savehtml specified. 
		Default is "png".

-width 		Wraps wide rules to accomodate into page of width specified.
		(Actually page will be wider)

input.g		The grammar file. Currently ANTLR only supported.

ANTLR libraries must be included into classpath.

Features:
1. Visualization of ANTLR synax in a form of diagram (Swing graphic)
2. Saving whole diagram to raster image formats like PING or JPEG.
3. Saving whole diagram to vector image format SVG.
4. Saving into multiple raster images and one consolidating HTML document. 
   Hyperlinks are supported for navigation.


This tool allows to visualize ANTLR synax as BNF diagrams.
You can also save the diagram in a variety of raster image formats. 
Currently PING and JPEG are supplied with JDK/JRE. 
Other formats are supported if their IIO providers available.
SDG2 chooses format by a file extention specified.

Vector image format SVG is supported. You have to use any SVG viewer or 
install SVG plugin in your browser. I tested this with Adobe's SVG plugin,
which is not perferct, in my opinion, but shows SVG image with great quality 
and allows some navigation.

Another useful feature is exporting into multiple raster image files.
Separate image file will be created for each rule, named with rule's name.
One HTML file will bind the whole picture pointing to files above.
The "image map" navigation is supported. Just click on oval element and go to 
a rule where it is expanded.

SDG2 does not support printing itself.
I did not try to test printing of saved diagrams. Assumable it should work from
any image viewer or HTML browser.

Although diagrams are black-and-whites, raster images are saved in RGB 24bite. 

SDG2 has been created on a base of SynDiag which is a GREAT tool,
but does not allow to save diagrams.
Author of SynDiag is Jennifer Zheng (jzheng@cs.usfca.edu).

Some SynDiag's antlr and java sources have been slightly modified to achieve 
more graphic accuracy.

I hope you enjoy it.

Mark Malakanov
mark.malakanov@rogers.com
