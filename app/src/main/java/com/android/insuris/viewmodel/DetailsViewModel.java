package com.android.insuris.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.android.insuris.model.Response;
import com.android.insuris.repository.DetailsRepository;

public class DetailsViewModel extends AndroidViewModel {

    private DetailsRepository detailsRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        detailsRepository =new DetailsRepository(application);
    }

    public LiveData<Response> getDetails(Integer number)
    {
        return detailsRepository.getMutableCommentsLiveData(number);
    }
}
