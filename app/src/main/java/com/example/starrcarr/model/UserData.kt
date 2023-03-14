package com.example.starrcarr.model

data class UserData(

    var id:String="",
    val firstName:String="",
    val lastName:String="",
    val email:String="",
    val phone:String="",
    val password:String="",
    val invitationCode:String="",
    var profitpercentage:Int=0,
    val numberOfTrips:Int=0,
    val numberOfsubscribers:Int=0,
)
