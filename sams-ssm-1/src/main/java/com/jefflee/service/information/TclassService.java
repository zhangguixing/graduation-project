package com.jefflee.service.information;

import java.util.List;

import com.jefflee.entity.information.Tclass;
import com.jefflee.po.information.TclassPo;

public interface TclassService {

	Integer insert(TclassPo tclassPo);

	List<TclassPo> selectAll();

	TclassPo selectById(Integer tclassId);

	Integer updateById(TclassPo tclassPo);

	Integer deleteById(Integer tclassId);

	String gnrName(Tclass tclass);

	String gnrShortName(Tclass tclass);

	List<Tclass> selectTclassList();
}
