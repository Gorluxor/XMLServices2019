const mysql = require('mysql');

let connection = mysql.createConnection({
    host: "35.188.219.237",
    user: "root",
    database: "Rating",
    password: "root"
});

connection.connect(function(err) {
    if (err) {
        console.error('Error connecting: ' + err.stack);
        return;
    }
    console.log('Connected as thread id: ' + connection.threadId);
});

module.exports = connection;