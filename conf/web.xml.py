#!/usr/bin/env python -OO

from string import Template
import json, argparse

WEB_XML = Template("""<?xml version="1.0" encoding="utf-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee">$mappings</web-app>
""")
SERVLET = Template("""
<servlet>
	<servlet-name>$name</servlet-name>
	<servlet-class>$class</servlet-class>
</servlet>
""")
SERVLET_MAPPING = Template("""
<servlet-mapping>
	<servlet-name>$name</servlet-name>
	$pattern
</servlet-mapping>
""")
PATTERN = Template("""<url-pattern>$pattern</url-pattern>""")

def main(args):
	f = open(args.ifile, "r")
	urls = dict(json = json.load(f), mappings = "")
	mappings = urls["json"].iteritems()
	for url, mapping in mappings:
		patterns = "\n\t".join([PATTERN.substitute({ "pattern" : pattern }) for pattern in mapping])
		urls["mappings"] += SERVLET.substitute({ "name" : url, "class" : url })
		urls["mappings"] += SERVLET_MAPPING.substitute({ "name" : url, "pattern" : patterns })
	open(args.ofile, "w").write(WEB_XML.substitute(urls))

if __name__ == "__main__":
	parser = argparse.ArgumentParser(description = "Maps a JSON file to a web.xml file.", usage = "%(prog)s [options]", add_help = False)
	parser.add_argument("--help", action = "help", help = "show this help message and exit")
	parser.add_argument("-i", dest = "ifile", help = "input URLs file", default = "urls.json")
	parser.add_argument("-o", dest = "ofile", help = "output mappings file", default = "web.xml")
	parser.add_argument("--version", action = "version", version = "2013.05.24")
	main(parser.parse_args())
