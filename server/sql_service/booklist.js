const query = require('../utils/utils');

const router = (req, res) => {
    let id = req.query.id||0
    query(id?`select *
           from booklist
           where id = '${id}';` : `select * from booklist;` , [1], (err, results,fields) => {
        if (err) throw err
       id? res.send(results[0]): res.send(result)
    })
}
module.exports=router
