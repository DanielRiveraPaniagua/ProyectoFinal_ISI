#!/bin/bash

#awk -F '\t' 'BEGIN{count=1} $5 == "actor," or $5 == "actress" {print count++ "\t" $2 "\t" $3 "\t" $4 }' imdb.txt > salida.txt
#awk '   BEGIN { while ((getline <"actores.txt") > 0) {REC[$1]=$0}}
#    {print REC[$1]}' <imdb.txt > salida.txt


awk -F '\t'  '{print $1 "\t" $2 "\t" $3 "\t" $4 }' data_1.tsv > salida.txt

#awk -F'\t' 'NR==FNR{a[$0];next} $1 in a' salida.txt imdb.txt > info.txt

#awk 'NR==FNR{A[$1,$2]=$3t$4;next} A[$1,$2]{print $1t$2t A[$1,$2] t$3t$4}' t="\t" salida.txt imdb.txt > info.txt
