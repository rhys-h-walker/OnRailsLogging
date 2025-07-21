# Commands used for publushing/versioning

## publish to GitHub Pages
mvn javadoc:javadoc
git worktree add gh-pages gh-pages
mkdir gh-pages\apidocs
xcopy /E /I /Y target\site\apidocs\* gh-pages\apidocs\
cd gh-pages
git add .
git commit -m "Updated JavaDoc"
git push origin gh-pages
cd ..
