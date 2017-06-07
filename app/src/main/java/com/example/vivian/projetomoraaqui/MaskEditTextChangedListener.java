package com.example.vivian.projetomoraaqui;

import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vivian on 05/06/2017.
 */

class MaskEditTextChangedListener implements TextWatcher{

    private static MaskEditTextChangedListener instance;

    private String mMask;
    private EditText edt_fone;
    private Set<String> symbolMask = new HashSet<String>();
    private boolean isUpdating;
    private String old = "";

    public MaskEditTextChangedListener(String mask, EditText editText) {
        mMask = mask;
        edt_fone = editText;
        initSymbolMask();
    }

    private void initSymbolMask(){
        for (int i=0; i < mMask.length(); i++){
            char ch = mMask.charAt(i);
            if (ch != '#')
                symbolMask.add(String.valueOf(ch));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = Utils.unmask(s.toString(), symbolMask);
        String mascara = "";

        if (isUpdating) {
            old = str;
            isUpdating = false;
            return;
        }

        if(str.length() > old.length())
            mascara = Utils.mask(mMask,str);
        else
            mascara = s.toString();

        isUpdating = true;

        edt_fone.setText(mascara);
        edt_fone.setSelection(mascara.length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }

}
