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

exports.getAllApprovedRatings = function getAllApprovedRatings(req, res) {
    connection.query("select * from rating where admin_approved = 1", (err, result)=> {
        if (err) res.status(400).send(err);
else {
        res.status(200).send(result);
    }
});
};

exports.getAllUnapprovedRatings = function getAllUnapprovedRatings(req, res) {
    connection.query("select * from rating where admin_approved = 0", (err, result)=> {
        if (err) res.status(400).send(err);
else {
        res.status(200).send(result);
    }
});
};

exports.addRating = function addRating(req, res) {
    let rating_value = req.body.rating_value;
    let comment = req.body.comment;
    let date = req.body.date;
    let admin_approved = req.body.admin_approved;
    let user_id = req.body.user_id;
    let accommodation_id = req.body.accommodation_id;
    let reservation_id = req.body.reservation_id;
    connection.query("insert into rating (rating_value, comment, date, admin_approved, user_id, accommodation_id, reservation_id) values (?,?,?,?,?,?,?)",[rating_value, comment, date, admin_approved, user_id, accommodation_id, reservation_id], (err, result) => {
        if (err) res.status(400).send(err);
else {
        res.status(200).send(result);
    }
});
};

exports.adminApproveComment = function adminApproveComment(req, res) {
    let id = req.body.id;
    console.log(id);
    connection.query("UPDATE rating SET admin_approved = 1 where id = " + id,
        (err, result)=> {
        if (err) { console.log(err); res.status(400).send(err); }
        else {
            res.status(200).send(result);
}
});
};