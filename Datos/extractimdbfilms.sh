#!/bin/sh
 awk '{if ($2=="movie")
              print $0;}' > movies.txt < ./data_film.tsv

sort -k4 -t "$(printf '\t')" movies.txt | uniq > moviesimdb.txt
