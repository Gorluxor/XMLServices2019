const connection = require('./baza')
var moment = require('moment');

exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from rating", (err, result)=> {
        if (err) res.status(400).send(err);
else {
        res.status(200).send(result);
    }
});
};