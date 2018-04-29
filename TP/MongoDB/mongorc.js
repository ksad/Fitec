//prompt = function() {user = db.runCommand({connectionStatus : 1}).authInfo.authenticatedUsers[0]; if (user) {return user.user + "@db[" + db +"] > "}; return ">"}

prompt = function() {
    user = db.runCommand({connectionStatus : 1}).authInfo.authenticatedUsers[0]
    if (user) {
        return user.user + "@db[" + db +"] > "
    }
    return ">"
}
