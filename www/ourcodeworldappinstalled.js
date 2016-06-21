/*global cordova, module*/

module.exports = {
    check: function(app_identifier,callbacks){
        if((!app_identifier) || (typeof(app_identifier) != "string")){
            throw new Error("The package name cannot be empty !");
        }

        cordova.exec(function(data){
            if(callbacks){
                if(data == "exists"){
                    if(callbacks.hasOwnProperty("success")){
                        callbacks.success();
                    }
                }else{
                    if(callbacks.hasOwnProperty("fail")){
                        callbacks.fail();
                    }
                }
            }
        }, function(err){
            if(callbacks.hasOwnProperty("error")){
                callbacks.error(err);
            }
        }, "OurCodeWorldappinstalled", "check", [{
            packagename: app_identifier
        }]);
    },
    open: function(app_identifier){
        cordova.exec(function(data){

        }, function(err){

        }, "OurCodeWorldappinstalled", "open", [{
            packagename: app_identifier
        }]);
    }
};
