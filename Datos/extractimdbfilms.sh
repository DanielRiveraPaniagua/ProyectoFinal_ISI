#!/bin/sh
#posiblemente incluir cortos
 awk '{if ($2=="movie")
              print $0;}'< ./data_film.tsv |
sort -k4 -t "$(printf '\t')" | uniq > moviesimdb.txt
