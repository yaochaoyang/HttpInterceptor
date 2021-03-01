package com.chaoyang.httpinterceptor

import com.chaoyang.network.MyRequest

/**
 *
 * @ClassName:      LoginRequest
 * @Description:    Description
 * @Author:         chaoyang
 * @CreateDate:     2/22/21 3:30 PM
 * @UpdateUser:     chaoyang
 * @UpdateDate:     2/22/21 3:30 PM
 * @UpdateRemark:   Modify the description
 */
data class LoginRequest(val name: String, val pwd: String) : MyRequest