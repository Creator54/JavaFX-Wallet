#!/usr/bin/env bash

SCRIPT_PATH=$(dirname "$(realpath -s "$0")")
VERSION=$(sed -n '2p' MANIFEST.MF | cut -d' ' -f2)

pushd $SCRIPT_PATH
javac -d . Main.java
jar cvmf MANIFEST.MF ${SCRIPT_PATH##*/}-$VERSION.jar *.class
rm -rf *.class
sed -i "s|$VERSION|$(echo ${VERSION} | awk -F. -v OFS=. '{$NF += 1 ; print}')|g" MANIFEST.MF
popd

