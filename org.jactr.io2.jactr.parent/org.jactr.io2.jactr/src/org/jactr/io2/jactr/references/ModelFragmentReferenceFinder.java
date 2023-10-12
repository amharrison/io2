package org.jactr.io2.jactr.references;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.findReferences.ReferenceFinder;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.Value;

import com.google.common.base.Predicate;

@SuppressWarnings("restriction")
public class ModelFragmentReferenceFinder extends ReferenceFinder {

	private Resource _lastResource;
	private Map<String, ChunkDef> _allChunks = new TreeMap<>();

	private Map<String, ChunkDef> getAllChunks(Resource localResource) {
		if (!(localResource.getContents().get(0) instanceof ModelFragment))
			return Collections.emptyMap();
		if (_allChunks == null || !localResource.equals(_lastResource)) {
			_lastResource = localResource;
			_allChunks.clear();
			EcoreUtil2.getAllContentsOfType(localResource.getContents().get(0), ChunkDef.class).forEach(def -> {
				_allChunks.put(def.getName(), def);
			});
		}
		return _allChunks;
	}

	protected void findLocalReferencesFromElement(Predicate<URI> targetURIs, EObject sourceCandidate,
			Resource localResource, Acceptor acceptor) {
		if (sourceCandidate instanceof Value) {
			Value value = (Value)sourceCandidate;
			if(value.getName()!=null)
			{
			  ChunkDef def = getAllChunks(localResource).get(value.getName());
			  if(def!=null)
			  {
				  URI refURI= EcoreUtil2.getPlatformResourceOrNormalizedURI(def);
				  URI sourceURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(sourceCandidate);
				  if(targetURIs.apply(refURI))
					  acceptor.accept(sourceCandidate, sourceURI, value.eContainmentFeature(), -1, value, refURI);
			  }
			}
		} else
			super.findLocalReferencesFromElement(targetURIs, sourceCandidate, localResource, acceptor);
	}

}
