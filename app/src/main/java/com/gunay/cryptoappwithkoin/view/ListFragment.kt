package com.gunay.cryptoappwithkoin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunay.cryptoappwithkoin.adapter.RecyclerAdapter
import com.gunay.cryptoappwithkoin.databinding.FragmentListBinding
import com.gunay.cryptoappwithkoin.model.CryptoModel
import com.gunay.cryptoappwithkoin.service.CryptoAPI
import com.gunay.cryptoappwithkoin.viewmodel.CryptoViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ListFragment : Fragment(), RecyclerAdapter.Listener, AndroidScopeComponent {


    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var cryptoAdapter = RecyclerAdapter(arrayListOf(), this)
    //koin ile viewModel inject
    private val viewModel by viewModel<CryptoViewModel>()

    //Android Scope Component'i kullanabilmek için spcope tanımlanması lazım
    //anotherModule'de scope yaptığımız inject'i yapabilmemiz için AndroidScopeComponent interfacesinin
    //zorunlu tuttuğu bu scope tanımlamasını yapman gerekiyor default olarak constructor içine alıyor ama
    //koin yardımıyla direk bu şekillerde const içine almadan tanımlayabiliyorsun
    override val scope: Scope by fragmentScope()
    //scope tanımı sonrasında anotherModule içerisinden inject etme işlemi
    //sanki utilden string çekiyormuş gibi koin scope ile bu şekilde string falan inject edilebiliyor
    private val hello by inject<String>(qualifier = named("hello"))

    /*
    //anlamsız ama bi gün gerekebilecek inject yontemleri
    //normal inject
    private val api = get<CryptoAPI>()
    //lazy inject -> kullanılana kadar initialize etmiyor
    private val apilazy by inject<CryptoAPI>()
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        //viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.getDataFromAPI()
        println(hello)

        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recyclerView.visibility = View.VISIBLE
                cryptoAdapter = RecyclerAdapter(ArrayList(it.data), this@ListFragment)
                binding.recyclerView.adapter = cryptoAdapter
            }
        })

        viewModel.cryptoEror.observe(viewLifecycleOwner, Observer{
            it?.let {
                if(it.data == true){
                    binding.cryptoErrorText.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }else{
                    binding.cryptoErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.data == true){
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.cryptoErrorText.visibility = View.GONE
                }else{
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //job?.cancel()
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(), "Clicked on : ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }


}

















