/*global cordova, module*/

module.exports = {
    check: function(app_identifier,callbacks){
        var _instance = this;

        if((!app_identifier) || (typeof(app_identifier) != "string")){
            throw new Error("The package name cannot be empty !");
        }

        cordova.exec(function(data){
            if(callbacks){
                if(data == "exists"){
                    if(callbacks.hasOwnProperty("success")){
                        var app = {
                            open: function(){
                                _instance.open(app_identifier);
                            }
                        };

                        callbacks.success(app);
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
    open: function(app_identifier,callbacks){
        cordova.exec(function(data){
            if(callbacks){
                if(callbacks.hasOwnProperty("success")){
                    callbacks.success();
                }
            }
        }, function(err){
            if(callbacks){
                if(callbacks.hasOwnProperty("error")){
                    callbacks.error(err);
                }
            }
        }, "OurCodeWorldappinstalled", "open", [{
            packagename: app_identifier
        }]);
    }
};
