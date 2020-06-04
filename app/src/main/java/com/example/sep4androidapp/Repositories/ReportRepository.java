package com.example.sep4androidapp.Repositories;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.connection.apis.ReportApi;
import com.example.sep4androidapp.connection.apis.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.apis.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.RatingResponse;
import com.example.sep4androidapp.connection.responses.ReportResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;
import com.example.sep4androidapp.connection.responses.SleepDataResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportRepository {
    private static ReportRepository instance;
    private MutableLiveData<RoomCondition> roomCondition;
    private MutableLiveData<SleepData> sleepData;
    private MutableLiveData<List<SleepSession>> sleepSessions;
    private MutableLiveData<List<SleepSession>> sleepSessionsDaily;
    private MutableLiveData<List<SleepSession>> sleepSessionsWeekly;
    private MutableLiveData<List<SleepSession>> sleepSessionsMonthly;
    private MutableLiveData<List<Device>> devices;
    private MutableLiveData<List<SleepSession>> sleepSessionsForFragment;

    private ReportRepository() {
        roomCondition = new MutableLiveData<>();
        sleepData = new MutableLiveData<>();
        sleepSessions = new MutableLiveData<>();
        devices = new MutableLiveData<>();
        sleepSessionsDaily = new MutableLiveData<>();
        sleepSessionsWeekly = new MutableLiveData<>();
        sleepSessionsMonthly = new MutableLiveData<>();
        sleepSessionsForFragment = new MutableLiveData<>();
    }

    public static synchronized ReportRepository getInstance() {
        if (instance == null) {
            instance = new ReportRepository();
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateRoomCondition(String deviceId) {

        RoomConditionApi roomConditionApi = ServiceGenerator.getRoomConditionApi();
        Call<RoomConditionResponse> call = roomConditionApi.getRoomCondition(deviceId);
        call.enqueue(new Callback<RoomConditionResponse>() {
            @Override
            public void onResponse(Call<RoomConditionResponse> call, Response<RoomConditionResponse> response) {
                if (response.code() == 200) {
                    roomCondition.setValue(response.body().getRoomCondition());
                } else if (response.code() == 204 || response.code() == 404) {
                    receiveLastRoomCondition(deviceId);
                } else {
                    Log.i("ReportRepo", "Response code received on updateRoomConditions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RoomConditionResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateRoomConditions: " + t.getMessage());
            }
        });
    }

    public void receiveLastRoomCondition(String deviceId) {
        RoomConditionApi roomConditionApi = ServiceGenerator.getRoomConditionApi();
        Call<RoomConditionResponse> call = roomConditionApi.getLastRoomCondition(deviceId);
        call.enqueue(new Callback<RoomConditionResponse>() {
            @Override
            public void onResponse(Call<RoomConditionResponse> call, Response<RoomConditionResponse> response) {
                if (response.code() == 200) {
                    roomCondition.setValue(response.body().getRoomCondition());
                } else {
                    Log.i("ReportRepo", "Response code received on receiveLastRoomCondition: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RoomConditionResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at receiveLastRoomCondition: " + t.getMessage());
            }
        });
    }

    public void updateSleepData(int sleepId) {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<SleepDataResponse> call = reportApi.getSleepData(sleepId);
        call.enqueue(new Callback<SleepDataResponse>() {
            @Override
            public void onResponse(Call<SleepDataResponse> call, Response<SleepDataResponse> response) {
                if (response.code() == 200) {
                    sleepData.setValue(response.body().getSleepData());
                } else {
                    Log.i("ReportRepo", "Response code received on getRoomCondition: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SleepDataResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at getRoomCondition: " + t.getMessage());
            }
        });
    }

    public void updateSleepSessions(String deviceId) {
        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusMonths(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        monthAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId, today.toString(), monthAgo.toString());
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200) {
                    sleepSessions.setValue(response.body().getSleepSessions());
                } else {
                    Log.i("ReportRepo", "Response code received on updateSleepSessions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateSleepSessions: " + t.getMessage());
            }
        });
    }

    public void updateSleepFragmentSessions(String deviceId) {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200) {
                    sleepSessionsForFragment.setValue(response.body().getSleepSessions());
                } else {
                    Log.i("ReportRepo", "Response code received on updateSleepFragmentSessions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateSleepFragmentSessions: " + t.getMessage());
            }
        });
    }

    public void rateSleep(int sleepId, int rating) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<RatingResponse> call = sleepTrackingApi.rateSleep(sleepId, rating);
        call.enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if (response.code() == 200) {
                } else {
                    Log.i("ReportRepo", "Response code received on rateSleep: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at rateSleep: " + t.getMessage());
            }
        });
    }

    public void updateDailySleepSessions(String deviceId, String today, String then) {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId, then, today);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200) {
                    sleepSessionsDaily.setValue(response.body().getSleepSessions());
                } else {
                    Log.i("ReportRepo", "Response code received on updateDailySleepSessions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateDailySleepSessions: " + t.getMessage());
            }
        });
    }

    public void updateWeeklySleepSessions(String deviceId, String today, String then) {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId, then, today);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200) {
                    sleepSessionsWeekly.setValue(response.body().getSleepSessions());
                } else {
                    Log.i("ReportRepo", "Response code received on updateWeeklySleepSessions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateWeeklySleepSessions: " + t.getMessage());
            }
        });
    }

    public void updateMonthlySleepSessions(String deviceId, String today, String then) {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId, then, today);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200) {
                    sleepSessionsMonthly.setValue(response.body().getSleepSessions());
                } else {
                    Log.i("ReportRepo", "Response code received on updateMonthlySleepSessions: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("ReportRepo", "Failure at updateWeeklySleepSessions: " + t.getMessage());
            }
        });
    }

    public LiveData<List<SleepSession>> getSleepSessionsDaily() {
        return sleepSessionsDaily;
    }

    public LiveData<List<SleepSession>> getSleepSessionsWeekly() {
        return sleepSessionsWeekly;
    }

    public LiveData<List<SleepSession>> getSleepSessionsMonthly() {
        return sleepSessionsMonthly;
    }

    public LiveData<List<SleepSession>> getSleepSessionForFragment() { return sleepSessionsForFragment; }

    public LiveData<List<SleepSession>> getSleepSessions() {
        return sleepSessions;
    }

    public LiveData<SleepData> getSleepData() {
        return sleepData;
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }

    public LiveData<RoomCondition> getRoomCondition() {
        return roomCondition;
    }
}
