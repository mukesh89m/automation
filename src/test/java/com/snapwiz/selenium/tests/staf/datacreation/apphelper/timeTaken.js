var db = db.getMongo().getDB("mongrel");

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}


var p = {
'149447' : '18971',
'149444' : '18971',
'149445' : '18971',
'149446' : '18971',
'149443' : '18971'
}

for (var key in p) {
  if (p.hasOwnProperty(key)) {
    var lsDashBoard = db.lsDashBoard.find({csId:NumberLong(p[key]), uId:NumberLong(key)});
    var orionDashboard = db.dashBoard.find({csId:NumberLong(p[key]), uId:NumberLong(key)});
  	var totalTime = 0;
  	var chapterId;
    var lsDashBoardUpdateDoc = {};
    	db.UserQuestionHistory.find({csId:NumberLong(p[key]), uId:NumberLong(key)}).forEach(function(uQhDoc){
            var ranTimeRaw = getRandomInt(100,150);
    		var ranTime = NumberLong(ranTimeRaw);
			chapterId = uQhDoc.chId;
    		db.UserQuestionHistory.update({csId:uQhDoc.csId, uId:uQhDoc.uId, qId:uQhDoc.qId},{$set : {time:ranTime}});
    		totalTime = totalTime + ranTime;
            
            if(lsDashBoard){            
                if(typeof lsDashBoardUpdateDoc['chapters.'+chapterId+'.time'] === "undefined"){
                    lsDashBoardUpdateDoc['chapters.'+chapterId+'.time'] = 0;
                }
                lsDashBoardUpdateDoc['chapters.'+chapterId+'.time'] = lsDashBoardUpdateDoc['chapters.'+chapterId+'.time'] + ranTimeRaw;
                for(tloCounter in uQhDoc.tloIds){
                    if(typeof lsDashBoardUpdateDoc['tlos.'+uQhDoc.tloIds[tloCounter]+'.time'] === "undefined"){
                        lsDashBoardUpdateDoc['tlos.'+uQhDoc.tloIds[tloCounter]+'.time'] = 0;
                    }
                    lsDashBoardUpdateDoc['tlos.'+uQhDoc.tloIds[tloCounter]+'.time'] = lsDashBoardUpdateDoc['tlos.'+uQhDoc.tloIds[tloCounter]+'.time'] + ranTimeRaw;
                }
            }
            if(orionDashboard){
                for(tloCounter in uQhDoc.tloIds){
                    db.dashBoard.update({csId:NumberLong(p[key]), uId:NumberLong(key), 'tlos.tloId' : uQhDoc.tloIds[tloCounter]},{$inc :{'tlos.$.time':ranTime}});                                                    
                }
                db.dashBoard.update({csId:NumberLong(p[key]), uId:NumberLong(key), 'chapters.chId':uQhDoc.chId},{$inc :{'chapters.$.time':ranTime}});
            }
    	});

        for(var objTempKey in lsDashBoardUpdateDoc){
            lsDashBoardUpdateDoc[objTempKey] = NumberLong(lsDashBoardUpdateDoc[objTempKey]);
        }
    	if(lsDashBoard){
    		db.lsDashBoard.update({csId:NumberLong(p[key]), uId:NumberLong(key)},{$set :lsDashBoardUpdateDoc});
    		db.lsDashBoard.update({csId:NumberLong(p[key]), uId:NumberLong(key)},{$set :{time:NumberLong(totalTime)}});
    		db.StudentCourseSummary.update({csId:NumberLong(p[key]), uId:NumberLong(key)},{$set :{ts:NumberLong(totalTime)}});
    		db.ClassSectionCourseSummary.update({csId:NumberLong(p[key])},{$inc :{hours:NumberLong(totalTime),tHours:NumberLong(totalTime + 500)}});
    	}
    	db.ClassSectionActivity.update({csId:NumberLong(p[key])},{$inc :{hours:NumberLong(totalTime)}});
    	db.ClassSectionPerformance.update({csId:NumberLong(p[key])},{$inc :{tSpent:NumberLong(totalTime)}});
  }
}
