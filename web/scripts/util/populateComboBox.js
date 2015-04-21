/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author Gulrez Sohliya
 *
 *  */
function PopulateCombo(oTarget,url){
    // function to populate a ComboBox from a Data  Source
    // Accepts 2 arguments
    // The Name of the Combo Box and
    // the URL of the Servlet
    var strParams = 'f=' + oTarget.form.name +"&e=" + oTarget.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}

function PopulateDependentComboFromTwoCondition(oElem0,oElem1,oTarget,url){
    //University Combo 
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 4 arguments
    // The Name of the Source Combo Box initiating the event i.e. oElem0,oElem1
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    // var strValue0 = oElem0.options[oElem0.selectedIndex].value; 
    
    
    var strValue0 = oElem0.toString(); // from text box 
    var strValue1 = oElem1.toString(); 
       
    var strParams = 'q0=' + strValue0 + "&q1="+ strValue1 + "&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem1.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}

function PopulateDependentComboId(oElem,oTarget,url){
   
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 3 arguments
    // The Name of the Source Combo Box initiating the event
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strValue = oElem.toString();
    var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}


function PopulateComboFromValue(queryString,targetCombo,url){

    // function to populate a Dependent ComboBox+oElem.name;
    // Accepts 3 arguments
    // Value with which the combo is to be populated in the form of a Query String
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strParams = "1=1&";
    strParams = strParams + queryString +"&f=" + targetCombo.form.name +"&e=" + targetCombo.name;
    //alert(strParams);
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}


function PopulateDependentCombo(oElem,oTarget,url){
   
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 3 arguments
    // The Name of the Source Combo Box initiating the event
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strValue = oElem.options[oElem.selectedIndex].value;
    var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem.name;
    //alert(strParams)
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}


function PopulateDefaultDistricts(oElem,oTarget,url){
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 3 arguments
    // The Name of the Source Combo Box initiating the event
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strValue = '23'; // State Code of Meghalaya
    var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}

function PopulateDistrictsFromStateCode(statecode,oTarget,url){
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 3 arguments
    // The Name of the Source Combo Box initiating the event
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strValue = statecode; // State Code of Meghalaya
    var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}

function PopulateCastesTribesCombo(oElem,oTarget,url){
    // function to populate a Dependent ComboBox from a Data  Source
    // Accepts 3 arguments
    // The Name of the Source Combo Box initiating the event
    // The Name of the Target Combo Box to be populated
    // the URL of the Servlet
    var strValue = oElem.value;
    //alert(oTarget.name + "  Populating Castes/Tribes " + strValue);
    if(strValue==undefined){
        strValue="ST";
    }
    var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem.name;
    var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}






//----------- populate if parents also belong to OBC ---------------
// function to populate a communities if father/mother is OBC
// Accepts 2 arguments
// flag(Y/N)to specify whether OBC or not
// The Name of the Target Combo Box to be populated
function isParentOBCCombo(isOBC,targetFormElement){

    targetFormElement.options.length=0;

    if(isOBC.value=="Y"){
        PopulateCombo(targetFormElement,'../PopulateOBCs');
    }
    else{
        var option = new Option('-',-1);
        targetFormElement.options.add(option,1)
    }

}
//---------- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-------

/*
function FillCombo(oElem,oTarget,url){
        var strValue = oElem.options[oElem.selectedIndex].value;
        url = url + '/officeComboServlet';
        var strParams = 'q=' + strValue +"&f=" + oTarget.form.name +"&e=" + oTarget.name+"&s="+oElem.name;
        var loader1 = new net.ContentLoader(url,FillDropDown,null,"POST",strParams);
}
*/



//this is to chnage the combo value -1 to "" [null] for jquery validator to be able to detect unselect combo
function FillDropDownforMinusOneValue(){
    var xmlDoc = this.req.responseXML.documentElement;

    var xSel = xmlDoc.getElementsByTagName('selectElement')[0];
    var strFName = xSel.childNodes[0].firstChild.nodeValue;
    var strEName = xSel.childNodes[1].firstChild.nodeValue;

    var objDDL = document.forms[strFName].elements[strEName];
    objDDL.options.length = 0;
    var xRows = xmlDoc.getElementsByTagName('entry');
    
    for(i=0;i<xRows.length;i++){
        var theText = xRows[i].childNodes[0].firstChild.nodeValue;
        var theValue = xRows[i].childNodes[1].firstChild.nodeValue;
        if(theValue==-1)
        {
            theValue="";   
        }
        var option = new Option(theText,theValue);
        objDDL.options.add(option,objDDL.options.length);
    }
}

function FillDropDown(){
    var xmlDoc = this.req.responseXML.documentElement;

    var xSel = xmlDoc.getElementsByTagName('selectElement')[0];
    var strFName = xSel.childNodes[0].firstChild.nodeValue;
    var strEName = xSel.childNodes[1].firstChild.nodeValue;

    var objDDL = document.forms[strFName].elements[strEName];
    objDDL.options.length = 0;
    var xRows = xmlDoc.getElementsByTagName('entry');
    
    for(i=0;i<xRows.length;i++){
        var theText = xRows[i].childNodes[0].firstChild.nodeValue;
        var theValue = xRows[i].childNodes[1].firstChild.nodeValue;
        var option = new Option(theText,theValue);
        objDDL.options.add(option,objDDL.options.length);
    }
//option = new Option("Others","others");
//    objDDL.options.add(option,objDDL.options.length);
}