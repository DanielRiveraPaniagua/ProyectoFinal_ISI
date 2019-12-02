#!./libs/bats/bin/bats

load 'libs/bats-support/load'
load 'libs/bats-assert/load'


@test "juntar archivos" {
    assert_equal $(for archivo in $(ls ../../../data/datosPeliculasActores/*.txt); 
    do
		join $archivo 
	done) datosMergeados.txt
}

@test "eliminar duplicados" {
    assert_equal $(cat awk '!seen[$0]++' datosMergeados.txt) movies.txt
}



