!/bin/bash

find . -type l | sed -e s'/^\.\///g' >> .gitignore