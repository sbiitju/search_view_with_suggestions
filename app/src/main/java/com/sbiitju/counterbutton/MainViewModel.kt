import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val searchResult = MutableLiveData<List<String>?>()
    val myList = ArrayList<String>()
    init {
        myList.add("Shahin")
        myList.add("Bashar")
        myList.add("Md")

    }
    fun searchAddress(query: String) {
        if (query == "") {
            searchResult.postValue(null)
        } else {
            searchResult.postValue(myList.filter {
                it.contains(query)
            })
        }
    }

}