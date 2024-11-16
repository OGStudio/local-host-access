PORT=8080

# 1. Get current working dir
curl localhost:$PORT/path

# 2. Get list of files of '.'
curl -X POST -d '.' localhost:$PORT/list
