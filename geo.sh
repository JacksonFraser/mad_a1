#!/bin/bash
HOST='localhost'
PORT='5554'
CMD1='auth aLAmflaK1Kc2Ykwc'
CMD2='geo fix 144.96339 -37.808481'

(
echo open "$HOST $PORT"
sleep 2
echo "$CMD1"
sleep 2
echo "$CMD2"
echo "exit"
) | telnet

