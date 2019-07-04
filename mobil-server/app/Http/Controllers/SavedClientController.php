<?php

namespace App\Http\Controllers;
use App\SavedClient;
use Illuminate\Http\Request;

class SavedClientController extends Controller
{
    public function getSavedClients(Request $request){
        $savedClient = SavedClient::get();
        return response()->json($savedClient, 200);
    }
    public function createSavedClient(Request $request){
        $savedClient = SavedClient::create($request->json()->all());
        return response()->json($savedClient, 201);
    }
    public function updateSavedClient(Request $request){
        $savedClient = SavedClient::findOrFail( $request->id);
        $response = $savedClient->update([
        ]);
        return response()->json($response, 201);
    }
}
