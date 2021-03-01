package com.chaoyang.httpinterceptor

import com.chaoyang.network.MyResult

/**
 *
 * @ClassName:      LoginResult
 * @Description:    Description
 * @Author:         chaoyang
 * @CreateDate:     2/22/21 3:31 PM
 * @UpdateUser:     chaoyang
 * @UpdateDate:     2/22/21 3:31 PM
 * @UpdateRemark:   Modify the description
 */
data class LoginResult(val session: String?) : MyResult