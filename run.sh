
echo "removing the repo"
rm -rf indexer

echo "clone repo"
git clone https://github.com/pavansachi/indexer.git

currentDir=`pwd`

echo $currentDir/indexer/results/stats.csv

now=`date +"%m-%d-%Y"`

service=service-qualification

message="$service	10	0	100	$now"
echo $message >> $currentDir/indexer/results/stats.csv

echo "change directory to results"
cd indexer/results

echo "commit test results"
git commit -m "test results" stats.csv
git push

