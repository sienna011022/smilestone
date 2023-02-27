package com.smilestone.smarket.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.data.User
import com.smilestone.smarket.data.User.id
import com.smilestone.smarket.data.User.nickname
import com.smilestone.smarket.dto.*
import com.smilestone.smarket.signup.SignUpViewModel
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime

object ConnectService {
    var signUpData: SignUp? = null


    //초기 retrofit 빌드
    val retrofit = Retrofit.Builder().baseUrl("http://52.78.175.29:8088")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var homeService: ProductService = retrofit.create(ProductService::class.java)
    lateinit var userService: UserService

    var retrofitToken :Retrofit? = null

    fun getToken(token: String){
        Log.d("테스트 토큰", token)
        val jwtClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(token)).build()
        retrofitToken = Retrofit.Builder().client(jwtClient)
                .baseUrl("http://52.78.175.29:8088").addConverterFactory(nullOnEmptyConverterFactory)
                .addConverterFactory(GsonConverterFactory.create()).build()
        homeService = retrofitToken!!.create(ProductService::class.java)
        userService = retrofitToken!!.create(UserService::class.java)
    }


    //로그인 서비스
    val loginService: LoginService = retrofit.create(LoginService::class.java)
    fun login(id: String, pw: String, code: MutableLiveData<Int>, loginMessage: MutableLiveData<Login>){
        val data = LoginData(id, pw)
        loginService.requestLogin(data)
            .enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginMessage.value = response.body()
                    code.value = response.code()
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("로그인", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //JWT 로그인 서비스
    fun jwtLogin(token: String?, id: String?, code: MutableLiveData<Int>, loginMessage: MutableLiveData<Login>) {
        val jwtClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(token)).build()
        val retrofit: Retrofit by lazy {
            Retrofit.Builder().client(jwtClient)
                .baseUrl("http://52.78.175.29:8088")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        Log.d("테스트 로그인", id.toString())
        val jwtLoginService: LoginService = retrofit.create(LoginService::class.java)
        jwtLoginService.requestJWTLogin(token ?: "", id ?: "")
            .enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginMessage.value = response.body()
                    code.value = response.code()
                    Log.d("로그인 테스트", code.value.toString())
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //회원가입 서비스
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)
    fun signUp(id: String, pw: String, nickname: String, code: MutableLiveData<Int>) {
        val data = SignUpData(id, pw, nickname)
        signUpService.requestSignUp(data)
            .enqueue(object : Callback<SignUp> {
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    signUpData = response.body()
                    code.value = response.code()
                    Log.d("SignUp확인", response.toString())
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.d("SignUp확인", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }

    //중복확인 서비스
    fun checkRedundancy(userId: String, code: MutableLiveData<Int>, result: MutableLiveData<Boolean>){
        signUpService.requestRedundancy(userId)
            .enqueue(object : Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    code.value = response.code()
                    result.value = response.body()
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    code.value = CODE_FAIL
                    Log.d("중복확인", t.message.toString())
                }

            })

    }


    //홈 화면 서비스

    fun home(code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        homeService.requestProducts(User.token!!)
            .enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                    //Log.d("테스트", post.value?.get(0).toString())
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("Home", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //판매 리스트 서비스
    fun getSellList(code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        homeService.getSellList(User.token!!, User.id!!)
            .enqueue(object: Callback<ArrayList<Product>>{
                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("SellList", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }

    //구매 리스트 서비스
    fun getPurchaseList(code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        homeService.getPurchaseList(User.token!!, User.id!!)
            .enqueue(object: Callback<ArrayList<Product>>{
                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("PurchaseList", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }


    //검색 서비스
    fun search(title: String, code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        Log.d("테스트", title)
        homeService.requestSearch(User.token!!, title)
            .enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                    Log.d("테스트", response.body().toString())
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.d("테스트", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //글 올리기 서비스
    fun upload(
        title: String = "",
        content: String = "",
        price: Long = 0,
        category: String = "기타",
        code: MutableLiveData<Int>
    ){
        val editData = EditData(User.id!!, title, content, price, category)
        Log.d("테스트 아이디", User.id.toString())
        homeService.uploadProduct(User.token!!, editData)
            .enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code.value = response.code()
                    Log.d("확인용", response.body().toString())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Upload", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }


    //글 보기 서비스
    fun item(id: Long, code: MutableLiveData<Int>, product: MutableLiveData<Product>) {
        homeService.getItem(User.token!!, id).enqueue(
            object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code.value = response.code()
                    product.value = response.body()
                    Log.d("테스트 아이템", response.body().toString())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Item", t.message.toString())
                }

            }
        )
    }

    //글 삭제 서비스
    fun deleteProduct(productId: Long, code:MutableLiveData<Int>){
        homeService.deleteProduct(User.token!!, productId)
            .enqueue(object : Callback<Long>{
                override fun onResponse(call: Call<Long>, response: Response<Long>) {
                    Log.d("글 삭제" , "글 삭제 완료")
                }

                override fun onFailure(call: Call<Long>, t: Throwable) {
                    Log.d("글 삭제" , t.message.toString())
                }

            })
    }

    //글 수정 서비스
    fun changeProduct(productId: Long = 0,
                      sellerId: Long = 0,
                       title: String = "",
                       content: String = "",
                       price: Long = 0,
                      category: String = "기타",
                        view: Long = 0){
        val product = ChangeProduct(category = category, view = view, productId = productId, sellerId = sellerId, title = title, content = content, price= price, localDateTime = LocalDateTime.now().toString(), state = false )
        homeService.changeProduct(User.token!!, product)
            .enqueue(object : Callback<Long>{
                override fun onResponse(call: Call<Long>, response: Response<Long>) {
                    Log.d("글 수정" , "글 수정 완료")
                }

                override fun onFailure(call: Call<Long>, t: Throwable) {
                    Log.d("글 수정" , t.message.toString())
                }

            })
    }

    //카테고리 검색 서비스
    fun getProduct(category: String?, code: MutableLiveData<Int>, product: MutableLiveData<ArrayList<Product>>){
        if(category==null)
            return
        homeService.getProduct(User.token!!, category)
            .enqueue(object : Callback<ArrayList<Product>>{
                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    product.value = response.body()
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    code.value = CODE_FAIL
                    Log.d("카테고리 검색", t.message.toString())
                }

            })
    }

    //유저 정보 받아오기
    fun getUser(result: MutableLiveData<Boolean>, id: Long){
        userService.getUser(User.token!!, id)
            .enqueue(object : Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    User.nickname = response.body()?.nickName ?: ""
                    result.value = true
                    Log.d("테스트", User.nickname.toString())
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.d("유저", t.message.toString())
                }

            })
    }

    //판매자 정보 받아오기
    fun getSellerUser(id: Long, nickname: MutableLiveData<String>){
        userService.getUser(User.token!!, id)
            .enqueue(object : Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    nickname.value = response.body()?.nickName ?: "익명"
                    Log.d("테스트 유저", id.toString())
                    Log.d("테스트 유저 ", response.code().toString())
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.d("테스트 유저", t.message.toString())
                }
            })
    }

    //닉네임 변경
    fun changeNickName(nickname: String, code: MutableLiveData<Int>){
        userService.changeNickName(User.token!!, User.nickname!!, nickname)
            .enqueue(object : Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    User.nickname = response.body()?.nickName
                    code.value = response.code()
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    code.value = CODE_FAIL
                    Log.d("닉네임 변경", t.message.toString())
                }

            })
    }

    //비밀번호 변경
    fun changePassword(password: String, newPassword: String,  code: MutableLiveData<Int>){
        userService.changePassword(User.token!!, User.id!!, password, newPassword)
            .enqueue(object : Callback<Long>{
                override fun onResponse(call: Call<Long>, response: Response<Long>) {
                    code.value = response.code()
                    Log.d("테스트 비밀번호", response.code().toString())
                }

                override fun onFailure(call: Call<Long>, t: Throwable) {
                    code.value = CODE_FAIL
                    Log.d("비밀번호 변경", t.message.toString())
                }

            })
    }

}

private val nullOnEmptyConverterFactory = object : Converter.Factory() {
    fun converterFactory() = this
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
        override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) {
            try{
                nextResponseBodyConverter.convert(value)
            }catch (e:Exception){
                e.printStackTrace()
                null
            }
        } else{
            null
        }
    }
}
