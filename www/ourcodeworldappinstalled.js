/*global cordova, module*/

module.exports = {
    check: function(app_identifier,callbacks){
        cordova.exec(function(data){
             console.info(data);
        }, function(err){
            console.error(err);
        }, "OurCodeWorldappinstalled", "check", [_settings]);
    }
};
