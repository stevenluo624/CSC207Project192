# this shows total lines in Java files that were added/removed for each author

parse_plus_minus () {

  printf "%-10s|%9d|" "$2" "$1"
  printf "%13s|" `git log --numstat --pretty="%H" --author="$2" ./ | awk '/\.*\.java$/ {plus+=$1} END {printf("%d",plus)}'`
  printf "%14s\n" `git log --numstat --pretty="%H" --author="$2" ./ | awk '/\.*\.java$/ {minus+=$2} END {printf("%d",minus)}'`

}

parse_repo_git_log () {

#cd $1
echo "=============================================="
echo "git log summary"
echo ""
echo "---------"
echo ""	
echo "user name | commits | lines added | lines deleted"
echo "----------|--------:|------------:|-------------:"
#get number of commits per user (or do email?)
git log --format='%aN <%aE>' | awk '{arr[$0]++} END{for (i in arr){print arr[i], i;}}' | sort -rn | while read line ; do parse_plus_minus ${line} ; done

cd ".."
}

parse_repo_git_log
