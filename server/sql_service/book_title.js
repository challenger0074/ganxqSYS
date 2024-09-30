const query = require('../utils/utils');

const router = (req, res) => {
    let id = req.query.id
    query(id?`select *
           from booklist
           where id = '${id}';` : `select * from booklist;` , [1], (err, result) => {
        if (err) throw err
        res.send(result)
    })
}
module.exports= router
