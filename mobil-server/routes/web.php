<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/saved_clients', 'SavedClientController@getSavedClients');
Route::post('/saved_clients', 'SavedClientController@createSavedClient');
Route::put('/saved_clients', 'SavedClientController@updateSavedClient');