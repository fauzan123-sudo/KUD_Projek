package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.penjualan.R
import com.example.penjualan.adapter.AdapterDataObat
import com.example.penjualan.adapter.AdapterKategori
import com.example.penjualan.databinding.FragmentHomeBinding
import com.example.penjualan.model.Resource
import com.example.penjualan.network.ApiInterFace
import com.example.penjualan.network.response.Data
import com.example.penjualan.repository.DataObatRepository
import com.example.penjualan.ui.viewModel.DataObatViewModel
import com.example.penjualan.util.handleApiError
import com.example.penjualan.util.visible
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_kategori.*
import kotlinx.android.synthetic.main.item_kategori.view.*


class Home : BaseFragment<DataObatViewModel, FragmentHomeBinding, DataObatRepository>(), RecyclerViewClickListener  {

    private val adapter by lazy { AdapterDataObat(requireContext()) }
//    private val adapterKatergori by lazy { AdapterKategori(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        init
        binding.recV.adapter = adapter
        binding.recV.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.recV.setHasFixedSize(true)

        adapter.listener = this

        viewModel.getObat()
        viewModel.obat.observe(viewLifecycleOwner) {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value.data)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        binding.progressbar.visible(false)

        viewModel.kategori()
        viewModel.kategori.observe(viewLifecycleOwner) {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    Log.d("names", "${it.value.data}")
                    val dataKategori = it.value.data



                    val adapters  = AdapterKategori(requireContext(),dataKategori)
                    spinKategori.adapter = adapters

                    binding.spinKategori.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val nama = view.id_kategori.text
                            binding.txtId.text = nama
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {

                        }
                    }

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        binding.imgCart.setOnClickListener {
            findNavController().navigate(HomeDirections.actionHomeToAllProduct())
        }

        binding.search.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val search_obat = binding.search.text.toString()
                    viewModel.cariObat(search_obat)
                    viewModel.cari_obat.observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Success -> {
                                updateUI(it.value.data)
                            }
                            is Resource.Failure -> {
                                handleApiError(it)
                                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Log.d("cek", "data tidak ada $it")
                            }
                        }
                    }
                    true
                }
                EditorInfo.IME_ACTION_DONE -> {
                    val search_obat = binding.search.text.toString()
                    viewModel.cariObat(search_obat)
                    viewModel.cari_obat.observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Success -> {
                                updateUI(it.value.data)
                            }
                            is Resource.Failure -> {
                                handleApiError(it)
                                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Log.d("cek", "data tidak ada $it")
                            }
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun updateUI(data: List<Data>) {
//        grid_layout.visibility = View.GONE
//        shimmer_layout.stopShimmer()
//        shimmer_layout.visibility = View.GONE
//        binding.recV.visibility = View.VISIBLE
        let { adapter.setDatas(data) }
    }

    override fun getViewModel() = DataObatViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): DataObatRepository {
        val api = remoteDataSource.buildApi(ApiInterFace::class.java)
        return DataObatRepository(api)
    }

    override fun onItemClicked(position: Int, data: List<Data>) {
        val dataName   = data[position].nama
        val dataImage  = data[position].foto
        val dataPrice  = data[position].harga
        val dataDetail = data[position].deskripsi
        val stockDetail = data[position].stok
        val sendData   = com.example.penjualan.model.detail.Data(dataImage, dataName, dataPrice, dataDetail,stockDetail)

        val action     = HomeDirections.actionHomeToDetailProduct(sendData)
        findNavController().navigate(action)

//        if(findNavController().currentDestination?.id ==R.id.home)
//            findNavController().navigate(
//                HomeDirections.actionHomeToDetailProduct()
//            )
//        else{
////            findNavController().navigate(
////                HomeDirections.actionHomeToDetailBarang(sendData)
////            )
//            Toast.makeText(requireContext(), "someone else", Toast.LENGTH_SHORT).show()
//        }
//        val action     = HomeDirections.actionHomeToDetailBarang(sendData)
//        findNavController().navigate(action)
//        Navigation.findNavController(requireView()).navigate(action)

    }

}
